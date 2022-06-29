export interface ICustomer {
  address: string;
  id: number;
  name: string;
}

export interface IOrder {
  completed: boolean;
  customerId: number;
  orderFromMenu: string;
  orderId: number;
  restaurantId: number;
}

export interface IRestaurant {
  address: string;
  id: number;
  menu: Record<string, string>;
  restaurantName: string;
}

export interface IOrderResponse {
  completed: boolean;
  customer: ICustomer;
  orderFromMenu: string;
  orderId: number;
  restaurant: IRestaurant;
}

export interface IRestaurantMenu {
  menu: Record<string, string>;
  restaurantName: string;
}
