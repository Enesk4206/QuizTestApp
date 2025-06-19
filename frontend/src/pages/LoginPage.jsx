import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../util/AuthContext';
const LoginPage = () => {
  const[username, setUsername] = useState('');
  const[password,setPassword] = useState('');
  const {login} =useAuth();
  const navigate = useNavigate();
  
  const handleLogin = async(e)=>{
    e.preventDefault();
    try {
      await login({username,password});
      navigate('/');
    } catch (error) {
      alert('Giriş Başarısız: '+(error.response?.data?.message || error.message));
    }
  }

  return (
    <div className='max-w-md mx-auto mt-14 rounded-md shadow-2xl p-8 bg-white'>
        <h2 className='text-2xl font-bold mb-4 text-center tracking-wider'>Giriş Sayfası</h2>

        <form onSubmit={handleLogin}>
          <div className='mb-4'>
                <label className='block mb-1 font-medium'>Kullanıcı Adı</label>
                <input 
                    type="text"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    className='w-full py-1 px-3 border rounded focus:outline-none focus:border-gray-700'
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
                    className='w-full py-1 px-3 border rounded focus:outline-none focus:border-gray-700'
                    placeholder='*********'
                    required
                />
            </div>
              <button className='w-full py-1 px-3 bg-sky-400 rounded shadow-2xl mt-2 text-white hover:bg-sky-500 focus:hover:bg-sky-600' type='submit'>
                Giriş
            </button>
        </form>
        <p className='p-1 text-sm my-3'>Kullanıcı hesabınız yok mu? <Link to={"/register"} className='text-sky-950'>Kayıt Sayfasına</Link></p>

    </div>
  )
}

export default LoginPage