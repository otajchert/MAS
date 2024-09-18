import axios from 'axios';

const API_URL = 'http://localhost:8080';

export const getClients = () => axios.get(`${API_URL}/clients`);
export const getEmployees = () => axios.get(`${API_URL}/employees`);
export const getProjects = () => axios.get(`${API_URL}/projects`);
export const getTeams = () => axios.get(`${API_URL}/teams`);
export const getEquipment = () => {return axios.get(`${API_URL}/equipment/all`).then(response => {console.log('Otrzymane dane:', response.data); return response.data;});};
export const getOrders = () => axios.get(`${API_URL}/orders`);
export const getInvoices = () => axios.get(`${API_URL}/invoices`);

export const addClient = (client) => axios.post(`${API_URL}/clients`, client);
export const addEmployee = (employee) => axios.post(`${API_URL}/employees`, employee);
export const addProject = (project) => axios.post(`${API_URL}/projects`, project);
export const addTeam = (team) => axios.post(`${API_URL}/teams`, team);
export const addEquipment = (equipment) => axios.post(`${API_URL}/equipment`, equipment);
export const addOrder = (order) => axios.post(`${API_URL}/orders`, order);
export const addInvoice = (invoice) => axios.post(`${API_URL}/invoices`, invoice);

export const updateClient = (id, client) => axios.put(`${API_URL}/clients/${id}`, client);
export const updateEmployee = (id, employee) => axios.put(`${API_URL}/employees/${id}`, employee);
export const updateProject = (id, project) => axios.put(`${API_URL}/projects/${id}`, project);
export const updateTeam = (id, team) => axios.put(`${API_URL}/teams/${id}`, team);
export const updateEquipment = (id, equipment) => axios.put(`${API_URL}/equipment/${id}`, equipment);
export const updateOrder = (id, order) => axios.put(`${API_URL}/orders/${id}`, order);
export const updateInvoice = (id, invoice) => axios.put(`${API_URL}/invoices/${id}`, invoice);

export const deleteClient = (id) => axios.delete(`${API_URL}/clients/${id}`);
export const deleteEmployee = (id) => axios.delete(`${API_URL}/employees/${id}`);
export const deleteProject = (id) => axios.delete(`${API_URL}/projects/${id}`);
export const deleteTeam = (id) => axios.delete(`${API_URL}/teams/${id}`);
export const deleteEquipment = (id) => axios.delete(`${API_URL}/equipment/${id}`);
export const deleteOrder = (id) => axios.delete(`${API_URL}/orders/${id}`);
export const deleteInvoice = (id) => axios.delete(`${API_URL}/invoices/${id}`);

export const getProjectEquipment = (projectId) => axios.get(`${API_URL}/projects/${projectId}/equipment`);

export const saveData = () => {
  return axios.post(`${API_URL}/api/save-data`);
};

export const getProjectById = (id) => axios.get(`${API_URL}/projects/${id}`);
export const getUserProjects = (userId) => axios.get(`${API_URL}/projects?userId=${userId}`);
export const getProjectsByManagerId = (managerId) => {
    console.log(`Requesting projects for managerId: ${managerId} from ${API_URL}/projects/manager/${managerId}`);
    return axios.get(`${API_URL}/projects/manager/${managerId}`);


  };

