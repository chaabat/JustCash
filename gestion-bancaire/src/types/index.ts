export interface Customer {
  id: number;
  name: string;
  email: string;
  address: string;
}

export interface Account {
  id: number;
  solde: number;
  type: "EPARGNE" | "COURANT";
  clientId: number;
  createdAt: string;
}

export interface CustomerResponse {
  content: Customer[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}

export interface AccountResponse {
  content: Account[];
  totalElements: number;
  totalPages: number;
  size: number;
  number: number;
}
