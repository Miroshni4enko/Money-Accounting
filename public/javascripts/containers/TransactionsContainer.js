import { connect } from 'react-redux'
import { fetchTransactions, fetchTransactionsSuccess, fetchTransactionsFailure } from '../actions/transactions';
import TransactionsList from '../components/TransactionsList';


const mapStateToProps = (state) => {
  return { 
    transactionsList: state.transactions.transactionsList
  };
}

const mapDispatchToProps = (dispatch) => {
  return {
    fetchTransactions: () => {
      dispatch(fetchTransactions()).then((response) => {
            !response.error ? dispatch(fetchTransactionsSuccess(response.payload.data)) : dispatch(fetchTransactionsFailure(response.payload.data));
          });
    }
  }
}

export default connect(mapStateToProps, mapDispatchToProps)(TransactionsList);