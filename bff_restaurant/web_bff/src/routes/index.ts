import * as express from "express";
import { getAllOrders, getRestaurantsMenu } from "../app/bff";

export const register = (app: express.Application) => {
  app.get("/", (req: express.Request, res: express.Response) => {
    res.send("Web BFF");
  });

  app.get("/bff/orders", (req: express.Request, res: express.Response) => {
    getAllOrders().then(
      (data) => {
        Promise.all(data).then((orders) => {
          res.send(orders);
        });
      },
      (reason) => {
        res.status(500).send(reason.toString());
      }
    );
  });

  app.get("/bff/menus", (req: express.Request, res: express.Response) => {
    getRestaurantsMenu().then(
      (menus) => {
        res.send(menus);
      },
      (reason) => {
        res.status(500).send(reason.toString());
      }
    );
  });
};
