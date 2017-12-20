import axios from 'axios';

//Transactions list
export const FETCH_TRANSACTIONS = 'FETCH_TRANSACTIONS';
export const FETCH_TRANSACTIONS_SUCCESS = 'FETCH_TRANSACTIONS_SUCCESS';
export const FETCH_TRANSACTIONS_FAILURE = 'FETCH_TRANSACTIONS_FAILURE';
export const RESET_TRANSACTIONS = 'RESET_TRANSACTIONS';


const ROOT_URL = 'http://localhost:9000'
export function fetchTransactions() {
  const request = axios({
    method: 'get',
    url: `${ROOT_URL}/transactions`,
    headers: []
  });

  return {
    type: FETCH_TRANSACTIONS,
    payload: request
  };
}

export function fetchTransactionsSuccess(transactions) {
  return {
    type: FETCH_TRANSACTIONS_SUCCESS,
    payload: transactions
  };
}

export function fetchTransactionsFailure(error) {
  return {
    type: FETCH_TRANSACTIONS_FAILURE,
    payload: error
  };
}