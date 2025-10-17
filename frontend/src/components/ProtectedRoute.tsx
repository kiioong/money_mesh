import React, { ReactElement } from 'react';
import { Redirect } from 'react-router-dom';
import { IonSpinner, IonContent } from '@ionic/react';
import { useAuth } from './AuthContext';

interface ProtectedRouteProps {
    children: ReactElement;
}

const ProtectedRoute: React.FC<ProtectedRouteProps> = ({ children }) => {
    const { user, loading } = useAuth();

    console.log(user);

    if (loading) {
        return (
            <IonContent className="ion-padding ion-text-center">
                <IonSpinner name="crescent" />
            </IonContent>
        );
    }

    if (!user) {
        return <Redirect to="/login" />;
    }

    return children;
};

export default ProtectedRoute;