import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import NavBar from './components/NavBar';
import HomePage from './components/HomePage';
import ProjectsPage from './components/ProjectsPage';
import AddEquipmentPage from './components/AddEquipmentPage';
import EquipmentPage from './components/EquipmentPage';
import ProjectDetailsPage from './components/ProjectDetailsPage'; 

function App() {
  return (
    <Router>
      <div>
        <NavBar />
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/projects" element={<ProjectsPage />} />
          <Route path="/projects/:projectId/equipment" element={<EquipmentPage />} />
          <Route path="/projects/:projectId/add-equipment" element={<AddEquipmentPage />} />
          <Route path="/projects/:projectId" element={<ProjectDetailsPage />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
