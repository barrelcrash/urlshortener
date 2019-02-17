import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import ShortenUrlForm from './ShortenUrlForm.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <ShortenUrlForm />
      </div>
    );
  }
}

export default App;
