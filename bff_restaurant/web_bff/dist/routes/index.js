"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.register = void 0;
const bff_1 = require("../app/bff");
exports.register = (app) => {
    app.get("/", (req, res) => {
        res.send("Web BFF");
    });
    app.get("/bff/orders", (req, res) => {
        bff_1.getAllOrders().then((data) => {
            Promise.all(data).then((orders) => {
                res.send(orders);
            });
        }, (reason) => {
            res.status(500).send(reason.toString());
        });
    });
    app.get("/bff/menus", (req, res) => {
        bff_1.getRestaurantsMenu().then((menus) => {
            res.send(menus);
        }, (reason) => {
            res.status(500).send(reason.toString());
        });
    });
};
//# sourceMappingURL=index.js.map