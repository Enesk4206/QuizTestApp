import { Link, NavLink } from 'react-router-dom'
import { useAuth } from '../util/AuthContext';
const Navbar = () => {
  const {user,isAuthenticated, isAdmin,logout} = useAuth();

  const navItemClass = ({isActive}) =>{
    isActive 
    ? 'border px-2 py-1 rounded bg-sky-500 text-white shadow-md'
    : 'py-1';
  }
  return (
    <nav className='w-full bg-amber-50 shadow-md'>
      <div className='max-w-7xl mx-auto px-3 md:px-5 lg:px-7'>
        <div className='flex flex-col md:flex-row items-center justify-between text-gray-600 h-16'>
          {/**logo */}
          <div className='flex text-gray-800 font-bold'>
            <Link to={"/"}>
              <h1 className='text-2xl font-bold tracking-tighter'>ÇözÖğren!</h1>
            </Link>
          </div>

          {/** content */}
          <div className='hidden md:flex space-x-6 gap-2 '>
            <NavLink to={"/"} className={navItemClass}>
              Anasayfa
            </NavLink>
            <NavLink to={"/category"} className={navItemClass}>
              Kategoriler
            </NavLink>
            <NavLink to={"/test"} className={navItemClass}>
              Testler
            </NavLink>
            <NavLink to={"/section"} className={navItemClass}>
              Bölümler
            </NavLink>
            <NavLink to={"/about"} className={navItemClass}>
              Hakkımızda 
            </NavLink>
            <NavLink to={"/contact"} className={navItemClass}>
              İletişim
            </NavLink>
          </div>

          {/** Authentication */}
          <div className='flex space-x-3 gap-2'> 
            {isAuthenticated ? (
              <div className="flex items-center gap-3">
                {isAdmin &&(
                  <>
                    <span className='font-bold text-red-500'>Admin Hoşgeldin!</span>
                    <Link to={'/admin/dashboard'} className='border shadow-md px-2 py-1'>
                      Panele Git
                    </Link>
                  </>
                )}
                {!isAdmin &&(
                  <span className='text-sm text-gray-700'>Merhaba, <b>{user?.username}</b></span>
                
                )}
                <button onClick={logout} className='py-1 px-3 borde rounded text-gray-500 hover:bg-gray-200'>
                  Çıkış
                </button>
              </div>
            )
            :(
              <div className='flex space-x-5'>
                <Link to={'/register'} className='hover:text-blue-700'>Kaydol</Link>
                <Link to={'/login'} className='hover:text-blue-700'>Giriş</Link>  
              </div>
            )
              
            }
          </div>  
          
           
        </div>
      </div>
    </nav>
  )
}

export default Navbar