import axios from "axios";
import {
  ICustomer,
  IOrder,
  IOrderResponse,
  IRestaurant,
  IRestaurantMenu,
} from "../entities/entities";

export function getAllOrders() {
  return axios({
    method: "get",
    url: `${process.env.ORDER_URL}/orders`,
  }).then(
    (response) => {
      return (response.data as IOrder[]).map(async (rawOrder) => {
        return {
          completed: rawOrder.completed,
          customer: await Promise.resolve(getCustomerById(rawOrder.customerId)),
          orderFromMenu: rawOrder.orderFromMenu,
          orderId: rawOrder.orderId,
          restaurant: await Promise.resolve(
            getRestaurantById(rawOrder.restaurantId)
          ),
        } as IOrderResponse;
      });
    },
    (reason) => {
      return Promise.reject(reason);
    }
  );
}

export function getCustomerById(id: number) {
  return axios({
    method: "get",
    url: `${process.env.CUSTOMER_URL}/customers/${id}`,
  }).then(
    (response) => {
      const rawCustomer: ICustomer = response.data;
      return {
        address: rawCustomer.address,
        id: rawCustomer.id,
        name: rawCustomer.name,
      } as ICustomer;
    },
    (reason) => {
      return Promise.reject(reason);
    }
  );
}

export function getRestaurantById(id: number) {
  return axios({
    method: "get",
    url: `${process.env.RESTAURANT_URL}/restaurants/${id}`,
  }).then(
    (response) => {
      const rawRestaurant: IRestaurant = response.data;
      return {
        address: rawRestaurant.address,
        id: rawRestaurant.id,
        menu: rawRestaurant.menu,
        restaurantName: rawRestaurant.restaurantName,
      } as IRestaurant;
    },
    (reason) => {
      return Promise.reject(reason);
    }
  );
}

export function getRestaurantsMenu() {
  return axios({
    method: "get",
    url: `${process.env.RESTAURANT_URL}/restaurants`,
  }).then(
    (response) => {
      return (response.data as IRestaurant[]).map((rawRestaurant) => {
        return {
          menu: rawRestaurant.menu,
          restaurantName: rawRestaurant.restaurantName,
        } as IRestaurantMenu;
      });
    },
    (reason) => {
      return Promise.reject(reason);
    }
  );
}
