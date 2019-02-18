import React, { Component } from 'react';
import './App.css';
import ShortenUrlForm from './ShortenUrlForm.js';
import GetUrlForm from './GetUrlForm.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <h1>URL Shortener</h1>
        <div className="form-container">
          <ShortenUrlForm />
          <GetUrlForm />
        </div>
      </div>
    );
  }
}

export default App;
