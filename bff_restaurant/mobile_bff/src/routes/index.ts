import * as express from "express";
import { getAllOrders } from "../app/bff";

export const register = (app: express.Application) => {
  app.get("/", (req: express.Request, res: express.Response) => {
    res.send("Mobile BFF");
  });

  app.get("/bff/orders", (req: express.Request, res: express.Response) => {
    getAllOrders().then(
      (data) => {
        Promise.all(data).then((orders) => res.send(orders));
      },
      (reason) => {
        res.status(500).send(reason.toString());
      }
    );
  });
};
