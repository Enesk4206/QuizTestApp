import {Routes,Route} from "react-router-dom"
import Navbar from "./components/Navbar"
import Footer from "./components/Footer"
import HomePage from "./pages/HomePage"
import TestPage from "./pages/TestPage"
import SectionPage from "./pages/SectionPage"
import AboutPage from "./pages/AboutPage"
import ContactPage from "./pages/ContactPage"
import AdminDashboarPage from "./pages/Admin/AdminDashboarPage"
import CategoryPage from "./pages/CategoryPage"
import RegisterPage from "./pages/RegisterPage"
import LoginPage from "./pages/LoginPage"

const App = () => {
  return (
    <div className="flex flex-col min-h-screen">
      
      <Navbar/>
      <main className="flex-grow">
        <Routes>
          <Route path="/" element={<HomePage/>}/>
          <Route path="/category" element={<CategoryPage/>}/>
          <Route path="/test" element={<TestPage/>}/>
          <Route path="/section" element={<SectionPage/>}/>
          <Route path="/about" element={<AboutPage/>}/>
          <Route path="/contact" element={<ContactPage/>}/>

          <Route path="/register" element={<RegisterPage/>}/>
          <Route path="/login" element={<LoginPage/>}/>

          <Route path="/admin/dashboard" element={<AdminDashboarPage/>}/>
        </Routes>
      </main>
      <Footer/>

    </div>
  )
}

export default App