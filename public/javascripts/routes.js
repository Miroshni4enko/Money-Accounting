import React from 'react';
import { Route, IndexRoute } from 'react-router';

import App from './pages/App';
import PostsIndex from './pages/Transactions';

export default (
  <Route path="/" component={PostsIndex}>
  </Route>
);
