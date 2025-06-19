import axios from "./axios";


const register = async(userData) =>{
    const response = await axios.post('/auth/register',{
        ...userData,
        role:'USER',
    });
    return response.data;
};

const login = async(creadentials) =>{
    const response = await axios.post('/auth/login',creadentials);
    return response.data;
}

export default {register, login};