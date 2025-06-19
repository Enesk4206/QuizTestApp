import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../util/AuthContext';

const RegisterPage = () => {
    const [username, setUsername] = useState('');
    const [password,setPassword] = useState('');
    const [email,setEmail] = useState('');
    const [age,setAge] = useState('');
    const [phoneNumber, setPhoneNumber] = useState('');
    const {register} = useAuth();
    const navigate = useNavigate();

    const handleRegister = async(e) => {
        e.preventDefault();
        console.log(username,password,email,age,phoneNumber)
        try {
            await register({username,password,email,age,phoneNumber});
            navigate('/');
        } catch (error) {
            alert("Kayıt Başarısız: "+ (error.response?.data?.message || error.message));
        }
    }

  return (
    <div className='max-w-md mx-auto mt-14 rounded-md shadow-2xl p-8 bg-white'>
        <h2 className='text-2xl font-bold mb-4 text-center tracking-wider'>Kayıt Sayfası</h2>

        <form onSubmit={handleRegister}>
            <div className='mb-4'>
                <label className='block mb-1 font-medium'>Kullanıcı Adı</label>
                <input 
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className='w-full py-1 px-3 border  rounded focus:outline-none focus:border-gray-700'
                    placeholder='userx332'
                    required 
                />
            </div>
            <div className='mb-4'>
                <label className="block mb-1 font-medium">Şifre</label>
                <input 
                    type="password"
                    value={password}
                    onChange={(e)=> setPassword(e.target.value)}
                    className='w-full py-1 px-3 border  rounded focus:outline-none focus:border-gray-700'
                    placeholder='*********'
                    required
                />
            </div>
            <div className='mb-4'>
                <label className="block mb-1 font-medium">Email</label>
                <input 
                    type="text"
                    value={email}
                    onChange={(e)=> setEmail(e.target.value)}
                    className='w-full py-1 px-3 border  rounded focus:outline-none focus:border-gray-700'
                    placeholder='userx332@example.com'
                    required 
                />
            </div>
            <div className='mb-4'>
                <label className='block mb-1 font-medium'>Yaş</label>
                <input 
                    type="number"
                    value={age}
                    onChange={(e) => setAge(e.target.value)}
                    className='w-full py-1 px-3 border  rounded focus:outline-none focus:text-gray-700'
                    placeholder='Yaşınızı giriniz'
                    required
                />
            </div>
            <div className='mb-4'>
                <label className='block mb-1 font-medium'>Telefon Numarası</label>
                <input 
                    type="text"
                    value={phoneNumber}
                    onChange={(e) => setPhoneNumber(e.target.value)}
                    className='w-full py-1 px-3 border  rounded focus:outline-none focus:text-gray-700'
                    placeholder='+90 555 444 1212'
                    required
                />
            </div>
            <button className='w-full py-1 px-3 bg-sky-400 rounded shadow-2xl mt-2 text-white hover:bg-sky-500 focus:hover:bg-sky-600' type='submit'>
                Kaydol
            </button>

        </form>
        <p className='p-1 text-sm my-3'>zaten kullanıcı hesabınız var mı? <Link to={"/login"} className='text-sky-950'>Giriş Sayfasına</Link></p>
    </div>
  )
}

export default RegisterPage