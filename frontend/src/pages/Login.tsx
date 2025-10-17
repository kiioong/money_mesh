import React, { useState, FormEvent } from "react";
import {
  IonPage,
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
  IonItem,
  IonLabel,
  IonInput,
  IonButton,
  IonText,
  IonCard,
  IonCardContent,
} from "@ionic/react";
import { useAuth } from "../components/AuthContext";
import { Link, useHistory } from "react-router-dom";

const Login: React.FC = () => {
  const [username, setusername] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [error, setError] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const { login } = useAuth();
  const history = useHistory();

  const handleSubmit = async (e: FormEvent<HTMLFormElement>): Promise<void> => {
    e.preventDefault();
    setError("");
    setLoading(true);

    const result = await login(username, password);

    if (result.success) {
      history.push("/home");
    } else {
      setError(result.message || "Login failed");
    }

    setLoading(false);
  };

  return (
    <IonPage>
      <IonHeader>
        <IonToolbar>
          <IonTitle>Login</IonTitle>
        </IonToolbar>
      </IonHeader>
      <IonContent className="ion-padding">
        <IonCard>
          <IonCardContent>
            <form onSubmit={handleSubmit}>
              <IonItem>
                <IonInput
                  type="text"
                  label="E-Mail"
                  labelPlacement="floating"
                  value={username}
                  onIonChange={(e) => setusername(e.detail.value!)}
                  required
                />
              </IonItem>

              <IonItem>
                <IonInput
                  type="password"
                  label="Password"
                  labelPlacement="floating"
                  value={password}
                  onIonChange={(e) => setPassword(e.detail.value!)}
                  required
                />
              </IonItem>

              {error && (
                <IonText color="danger">
                  <p className="ion-padding">{error}</p>
                </IonText>
              )}

              <IonButton
                expand="block"
                type="submit"
                className="ion-margin-top"
                disabled={loading}
              >
                {loading ? "Logging in..." : "Login"}
              </IonButton>
              <Link to="/register">
                <IonButton
                  expand="block"
                  fill="outline"
                  className="ion-margin-top"
                  disabled={loading}
                >
                  Register
                </IonButton>
              </Link>
            </form>
          </IonCardContent>
        </IonCard>
      </IonContent>
    </IonPage>
  );
};

export default Login;
