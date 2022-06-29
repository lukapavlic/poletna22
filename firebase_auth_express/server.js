const express = require('express');
const app = express();
const cors = require('cors');

const admin = require('firebase-admin');
const credentials = require("./poletnasola22-firebase-adminsdk-awx7b-494e323173.json");

admin.initializeApp({
    credential: admin.credential.cert(credentials)
});

const db = admin.firestore();

app.use(express.json());

app.use(express.urlencoded({ extended: true }));

app.use(cors());

const authenticateJWT = async (req, res, next) => {
    const authHeader = req.headers.authorization;

    if (authHeader) {
        const idToken = authHeader.split(" ")[1];
        admin
            .auth()
            .verifyIdToken(idToken)
            .then(function (decodedToken) {
                return next();
            })
            .catch(function (error) {
                console.log(error);
                return res.sendStatus(403);
            });
    } else {
        res.sendStatus(401);
    }
};

app.post("/signup", async (req, res) => {
    const userResponse = await admin.auth().createUser(
        {
            email: req.body.email,
            password: req.body.password,
            emailVerified: false,
            disabled: false
        }
    );

    try {
        const id = req.body.email;
        const userJson = {
            email: req.body.email,
            firstName: req.body.firstName,
            lastName: req.body.lastName
        };
        const response = db.collection("users").doc(id).set(userJson);
        console.log(response);
    } catch (error) {
        console.log(error);
    }
    res.json(userResponse);
});

app.get("/getData/:id", authenticateJWT, async (req, res) => {
    try {
        const userRef = db.collection("users").doc(req.params.id);
        const user = await userRef.get();
        res.send(user.data());
    } catch (error) {
        res.send(error);
    }
});



const PORT = process.env.PORT || 8080;
app.listen(PORT, () => {
    console.log(`Server is listening on port ${PORT}`);
});