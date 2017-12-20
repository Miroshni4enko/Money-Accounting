import React, { Component } from 'react';
import { Link } from 'react-router';

class TransactionsList extends Component {
  componentWillMount() {
    this.props.fetchTransactions();
  }

  renderCategories(categories) {
     return categories.map((c) => {
        c = c.trim();
        return (
          <Link to={"filter/" + c} key={c} className="list-group-item-text">{" " + c + " "}</Link>
        );
     });
  }

  renderTransactions(transactions) {
    return transactions.map((post) => {
      return (
        <li className="list-group-item" key={post._id}>
          <Link style={{color:'black'}} to={"transactions/" + post._id}>
            <h3 className="list-group-item-heading">{post.title}</h3>
          </Link>
            {this.renderCategories(post.categories)}
        </li>
      );
    });
  }

  render() {
    const { transactions, loading, error } = this.props.transactionsList;

    if(loading) {
      return <div className="container"><h1>Transactions</h1><h3>Loading...</h3></div>      
    } else if(error) {
      return <div className="alert alert-danger">Error: {error.message}</div>
    }

    return (
      <div className="container">
        <h1>Transactions</h1>
        <ul className="list-group">
          {this.renderTransactions(transactions)}
        </ul>
      </div>
    );
  }
}


export default TransactionsList;
