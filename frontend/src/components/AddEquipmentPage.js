import React, { useEffect, useState } from 'react';
import { getEquipment } from '../services/ApiService';
import { useNavigate, useParams } from 'react-router-dom';
import '../index.css';

const AddEquipmentPage = () => {
  const [equipmentList, setEquipmentList] = useState([]); // Inicjalizacja jako pusta tablica
  const [selectedEquipment, setSelectedEquipment] = useState(null);
  const { projectId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getEquipment().then(data => {
        if (Array.isArray(data)) {
            setEquipmentList(data);
        } else {
            console.error('Oczekiwano tablicy, ale otrzymano coś innego:', data);
        }
    }).catch(error => {
        console.error("Błąd pobierania sprzętu:", error);
    });
}, []);

const handleAddEquipment = () => {
  if (selectedEquipment) {
      const payload = {
          equipmentId: selectedEquipment,
      };

      fetch(`/projects/${projectId}/equipment`, {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify(payload),
      })
      .then(response => {
          if (!response.ok) {
              throw new Error('Network response was not ok');
          }
          navigate(`/projects/${projectId}/equipment`);
      })
      .catch(error => {
          console.error('Błąd podczas dodawania sprzętu do projektu:', error);
      });
  }
};



  return (
    <div>
      <h1>Dodaj sprzęt do projektu</h1>
      <select onChange={(e) => setSelectedEquipment(e.target.value)}>
        <option value="">Wybierz sprzęt</option>
        {equipmentList.map(equipment => (
          <option key={equipment.id} value={equipment.id}>{equipment.name}</option>
        ))}
      </select>
      <button onClick={handleAddEquipment}>Dodaj sprzęt</button>
    </div>
  );
}

export default AddEquipmentPage;
