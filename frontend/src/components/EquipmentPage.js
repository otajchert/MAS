import React, { useEffect, useState } from 'react';
import { getProjectEquipment } from '../services/ApiService';
import { useNavigate, useParams } from 'react-router-dom';
import '../index.css';

const EquipmentPage = () => {
  const [equipmentList, setEquipmentList] = useState([]);
  const { projectId } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    getProjectEquipment(projectId).then(response => {
      setEquipmentList(response.data);
    }).catch(error => {
      console.error("Error fetching equipment:", error);
    });
  }, [projectId]);

  const handleAddEquipment = () => {
    navigate(`/projects/${projectId}/add-equipment`);
  };

  return (
    <div>
      <h1>Lista sprzętów dla projektu o ID {projectId}</h1>
      <ul>
        {equipmentList.length > 0 ? (
          equipmentList.map(equipment => (
            <li key={equipment.id}>{equipment.name}</li>
          ))
        ) : (
          <li>Brak sprzętów przypisanych do tego projektu.</li>
          
        )}
      </ul>
      <button onClick={handleAddEquipment}>Dodaj sprzęt</button>
    </div>
  );
}

export default EquipmentPage;
