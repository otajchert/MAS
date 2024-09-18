import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axios from 'axios';
import '../index.css';
import { saveData } from '../services/ApiService';

const NavBar = () => {
  const handleSaveData = () => {
    saveData().then(response => {
      console.log('Dane zostały zapisane do bazy:', response.data);
      alert('Dane zostały zapisane do bazy!');
    }).catch(error => {
      console.error('Błąd podczas zapisywania danych:', error);
      alert('Wystąpił błąd podczas zapisywania danych.');
    });
  };

  return (
    <nav>
      <ul>
        <li><Link to="/">Strona główna</Link></li>
        <li><Link to="/projects">Projekty</Link></li>
        <li style={{ float: 'right' }}><button onClick={handleSaveData}>Zapisz</button></li>
        <li style={{ float: 'right' }}><Link to="/">Olga</Link></li>
      </ul>
    </nav>
  );
}

export default NavBar;
