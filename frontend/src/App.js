import React, { Component } from 'react';
import './App.css';
import ShortenUrlForm from './ShortenUrlForm.js';
import GetUrlForm from './GetUrlForm.js';

class App extends Component {
  render() {
    return (
      <div className="App">
        <ShortenUrlForm />
        <GetUrlForm />
      </div>
    );
  }
}

export default App;
