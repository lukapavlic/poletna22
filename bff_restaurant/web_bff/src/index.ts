import cors from "cors";
import dotenv from "dotenv";
import express, { Request } from "express";
import * as routes from "./routes";

dotenv.config();

const port = process.env.SERVER_PORT;
const app = express();

app.use(cors<Request>());

routes.register(app);

app.listen(port, () => {
  // tslint:disable-next-line:no-console
  console.log(`Server started at http://localhost:${port}`);
});
