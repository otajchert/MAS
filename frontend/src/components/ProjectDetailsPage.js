
import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getProjectById } from '../services/ApiService';
import '../index.css';

const ProjectDetailsPage = () => {
  const { projectId } = useParams();
  const [project, setProject] = useState(null);

  useEffect(() => {
    getProjectById(projectId).then(response => {
      setProject(response.data);
    });
  }, [projectId]);

  if (!project) {
    return <div>Ładowanie...</div>;
  }

  return (
    <div>
      <h1>Szczegóły projektu: {project.name}</h1>
      <p>Data rozpoczęcia: {project.startDate}</p>
      <p>Status: {project.status}</p>
      <p>Budżet: {project.budget}</p>
      <p>Zamówienie: {project.order?.id}</p>
      <p>Zespół: {project.team?.name}</p>
      
    </div>
  );
}

export default ProjectDetailsPage;
