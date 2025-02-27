import axios from "axios";
import { Customer, Account, CustomerResponse, AccountResponse } from "../types";

const API_URL = "http://localhost:8080";

const api = axios.create({
  baseURL: API_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

export const customerApi = {
  getAll: (page = 0, size = 10, name?: string, sort: string = "id") =>
    api.get<CustomerResponse>(
      `/customer-service/api/customers?page=${page}&size=${size}${
        name ? `&name=${name}` : ""
      }&sort=${sort}`
    ),

  getById: (id: number) =>
    api.get<Customer>(`/customer-service/api/customers/${id}`),

  create: (customer: Omit<Customer, "id">) =>
    api.post<Customer>("/customer-service/api/customers", customer),

  update: (id: number, customer: Omit<Customer, "id">) =>
    api.put<Customer>(`/customer-service/api/customers/${id}`, customer),

  delete: (id: number) => api.delete(`/customer-service/api/customers/${id}`),
};

export const accountApi = {
  getAllByCustomerId: (customerId: number, page = 0, size = 10) =>
    api.get<AccountResponse>(
      `/account-service/api/accounts/client/${customerId}?page=${page}&size=${size}`
    ),

  create: (account: Omit<Account, "id" | "createdAt">) =>
    api.post<Account>("/account-service/api/accounts", account),

  delete: (id: number) => api.delete(`/account-service/api/accounts/${id}`),
};

export default api;
