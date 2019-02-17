import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';

class ShortenUrlForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      value: '',
      shortUrl: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {

    event.preventDefault();
  }

  
  render () {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          Shorten a url:
          <input
            type="text"
            value={this.state.value}
            onChange={this.handleChange} />
        </label>
        <input
          type="submit"
          value="Shorten" />
      </form>
    );
  }
}

export default ShortenUrlForm;
