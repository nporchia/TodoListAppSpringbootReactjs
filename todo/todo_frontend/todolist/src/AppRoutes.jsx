import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Layout from './components/Layout.jsx';
import CreateTodo from './components/CreateTodo.jsx';
import Login from './components/Login.jsx';
import AuthProvider from './context/AuthProvider.js';
const AppRoutes = () => {
    return (
        <AuthProvider>
            <Router>
                <Routes>
                    <Route path="/" element={<Login />}>
                    </Route>
                    <Route path="/home" element={<Layout />}>
                    </Route>
                    <Route path="/todos/create" element={<CreateTodo />}>
                    </Route>
                </Routes>
            </Router>
            </AuthProvider>
    )
}

export default AppRoutes