import React, { useEffect, useState } from 'react';
import { getProjectsByManagerId } from '../services/ApiService';
import { useNavigate } from 'react-router-dom';
import '../index.css';

const ProjectsPage = () => {
  const [projects, setProjects] = useState([]);
  const navigate = useNavigate();
  const loggedInManagerId = 1; 

  useEffect(() => {
    //console.log("Fetching projetow managera o id", loggedInManagerId);
    getProjectsByManagerId(loggedInManagerId).then(response => {
      //console.log("Fetched projects:", response.data);
      setProjects(response.data);
    }).catch(error => {
      //console.error("Error :", error);
    });
  }, []);
  

  const handleViewEquipment = (projectId) => {
    navigate(`/projects/${projectId}/equipment`);
  };

  return (
    <div>
      <h1>Lista projektów</h1>
      <table>
        <thead>
          <tr>
            <th>Nazwa projektu</th>
            <th>Data rozpoczęcia</th>
            <th>Status</th>
            <th>Akcje</th>
          </tr>
        </thead>
        <tbody>
          {projects.map(project => (
            <tr key={project.id}>
              <td>{project.name}</td>
              <td>{project.startDate}</td>
              <td>{project.status}</td>
              <td>
                <button onClick={() => handleViewEquipment(project.id)}>Lista sprzętów</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ProjectsPage;
