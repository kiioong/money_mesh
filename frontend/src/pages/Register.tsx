import {
  IonButton,
  IonCard,
  IonCardContent,
  IonContent,
  IonHeader,
  IonInput,
  IonItem,
  IonLabel,
  IonPage,
  IonText,
  IonTitle,
  IonToolbar,
} from "@ionic/react";
import React, { FormEvent, useState } from "react";
import { useAuth } from "../components/AuthContext";
import { Link, useHistory } from "react-router-dom";

const Register: React.FC = () => {
  const [email, setEmail] = useState<string>("");
  const [password, setPassword] = useState<string>("");
  const [confirmPassword, setConfirmPassword] = useState<string>("");
  const [error, setError] = useState<string>("");
  const [loading, setLoading] = useState<boolean>(false);
  const { register } = useAuth();
  const history = useHistory();

  const handleSubmit = async (e: FormEvent<HTMLFormElement>): Promise<void> => {
    e.preventDefault();
    setError("");

    if (password !== confirmPassword) {
      setError("passwords don't match");
      return;
    }

    setLoading(true);

    const result = await register(email, password);

    console.log(result);

    if (result.success) {
      history.push("/login");
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
                  value={email}
                  onIonChange={(e) => setEmail(e.detail.value!)}
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

              <IonItem>
                <IonInput
                  type="password"
                  label="Confirm Password"
                  labelPlacement="floating"
                  value={confirmPassword}
                  onIonChange={(e) => setConfirmPassword(e.detail.value!)}
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
            </form>
          </IonCardContent>
        </IonCard>
      </IonContent>
    </IonPage>
  );
};

export default Register;
