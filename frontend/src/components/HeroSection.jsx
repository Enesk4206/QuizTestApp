import React from 'react'
import{useAuth} from "../util/AuthContext"
import { Link } from 'react-router-dom';
const HeroSection = () => {
  const {isAuthenticated} = useAuth();
  return (
    <div className='max-w-7xl mx-auto p-2 md:p-4 lg:p-6'>
      <div className='flex flex-col md:flex-row gap-2 bg-gray-100 p-7'>
        {/**hero section image */}
        <div className='md:w-1/2 w-full'>
          <img src="/src/assets/images/hero-image.jpg" className='h-96 w-max shadow-2xl rounded bg-cover' />
        </div>
        <div className='flex flex-col md:w-1/2 w-full '>
          <h2 className='tracking-tighter mt-5 text-3xl'>Bizimle Birlikte Öğren Bizimle Birlikte Geliş!</h2>
          <h3 className='uppercase mt-2 tracking-tighter mt-5 text-2xl font-bold'>Çözöğren her zaman her yerde yanında</h3>
          <p className='text-gray-600 text text-2xl mt-3 font-bold'>Bizimle geleceğe hazılanın Türkiyedeki öğrencilerin platformu! Hazırsan hayat da başırılı olmaya o zaman hemen bize katıl</p>
          {!isAuthenticated ?(
            <Link to={'/register'} className='block border w-full bg-blue-500 py-2 text-white mt-20 text-center shadow'>Hemen Kaydol!</Link>
          ):(
              <Link to={'/test'} className='block border w-full bg-green-500 py-2 text-white mt-20 text-center shadow'>
                Testler Sayfasına
              </Link>
          )}
        </div>
      </div>

      
    </div>
  )
}

export default HeroSection