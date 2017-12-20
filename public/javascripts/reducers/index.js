import { combineReducers } from 'redux';
import TransactionsReducer from './reducer_transactions';

const rootReducer = combineReducers({
  user: UserReducer,
  validateFields: ValidateUserFieldsReducer,
  transactions: TransactionsReducer, //<-- Transactions
  form: formReducer, // <-- redux-form
  resendEmail: ResendEmailReducer,
  updateEmail: UpdateEmailReducer
});

export default rootReducer;
