"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.getRestaurantById = exports.getCustomerById = exports.getAllOrders = void 0;
const axios_1 = __importDefault(require("axios"));
function getAllOrders() {
    return axios_1.default({
        method: "get",
        url: `${process.env.ORDER_URL}/orders`,
    }).then((response) => {
        return response.data.map((rawOrder) => __awaiter(this, void 0, void 0, function* () {
            return {
                completed: rawOrder.completed,
                customer: yield Promise.resolve(getCustomerById(rawOrder.customerId)),
                orderFromMenu: rawOrder.orderFromMenu,
                orderId: rawOrder.orderId,
                restaurant: yield Promise.resolve(getRestaurantById(rawOrder.restaurantId)),
            };
        }));
    }, (reason) => {
        return Promise.reject(reason);
    });
}
exports.getAllOrders = getAllOrders;
function getCustomerById(id) {
    return axios_1.default({
        method: "get",
        url: `${process.env.CUSTOMER_URL}/customers/${id}`,
    }).then((response) => {
        const rawCustomer = response.data;
        return {
            address: rawCustomer.address,
            id: rawCustomer.id,
            name: rawCustomer.name,
        };
    }, (reason) => {
        return Promise.reject(reason);
    });
}
exports.getCustomerById = getCustomerById;
function getRestaurantById(id) {
    return axios_1.default({
        method: "get",
        url: `${process.env.RESTAURANT_URL}/restaurants/${id}`,
    }).then((response) => {
        const rawRestaurant = response.data;
        return {
            address: rawRestaurant.address,
            id: rawRestaurant.id,
            menu: rawRestaurant.menu,
            restaurantName: rawRestaurant.restaurantName,
        };
    }, (reason) => {
        return Promise.reject(reason);
    });
}
exports.getRestaurantById = getRestaurantById;
//# sourceMappingURL=bff.js.map