import { Navigate, Route, Routes } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import Home from './pages/home/Home';
import NotFound from './pages/page404/NotFound';
import SignIn from './pages/signin/SignIn';
import SignUp from './pages/signup/SignUp';
import User from './pages/user';

function AuthGuard({
    isAuthenticated,
    component,
}: {
    isAuthenticated: boolean;
    component: JSX.Element;
}) {
    return isAuthenticated ? component : <Navigate to="/signin" />;
}

function AdminGuard({
    isAuthenticated,
    isAdmin,
    component,
}: {
    isAuthenticated: boolean;
    isAdmin: boolean;
    component: JSX.Element;
}) {
    return isAuthenticated && isAdmin ? component : <Navigate to="/" />;
}

export default function RoutesHandler() {
    const { isAuthenticated, isAdmin } = useAuth();
    return (
        <Routes>
            <Route
                path="/"
                element={
                    <AuthGuard
                        component={<Home />}
                        isAuthenticated={isAuthenticated}
                    />
                }
            />
            <Route
                path="users"
                element={
                    <AdminGuard
                        component={<User />}
                        isAuthenticated={isAuthenticated}
                        isAdmin={isAdmin}
                    />
                }
            />
            <Route path="signin" element={<SignIn />} />
            <Route path="signup" element={<SignUp />} />
            <Route path="*" element={<NotFound />} />
        </Routes>
    );
}
