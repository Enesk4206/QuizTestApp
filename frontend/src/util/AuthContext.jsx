import React, { createContext, useContext, useState } from 'react'
import {useNavigate} from 'react-router-dom'
import authService from '../api/authService'
const AuthContext = createContext();

export const AuthProvider = ({children}) =>{
    const navigate = useNavigate();

    const[user,setUser] = useState(()=> {

        const savedUser = localStorage.getItem('user');
        return savedUser ? JSON.parse(savedUser) : null;
    });

    const login = async (credentals) =>{
        try {
            const data = await authService.login(credentals);
            setUser(data);
            localStorage.setItem('user',JSON.stringify(data));
            return data;
        } catch (error) {
            throw error.message;
        }
    };

    const register = async (userData)=>{
        try {
            const data = await authService.register(userData);
            setUser(data);
            localStorage.setItem('user',JSON.stringify(data));
            return data;
        } catch (error) {
            throw error.message;
        }
    };

    const logout = () => {
        setUser(null);
        localStorage.removeItem('user');
        navigate('/login');
    };

    return(
        <AuthContext.Provider value={{user,login,register,logout,isAuthenticated:!!user, isAdmin: user?.role ==='ADMIN',isUser:user?.role==='USER'}}>
            {children}
        </AuthContext.Provider>
    )
};

export const useAuth = ()=> useContext(AuthContext);