import React, { Component } from 'react';
import './App.css';

class GetUrlForm extends Component {

  constructor(props) {
    super(props);
    this.state = {
      value: '',
      originUrl: ''
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({value: event.target.value});
  }

  handleSubmit(event) {
    (async () => {
      const response = await fetch(`http://localhost:8080/short/get/${this.state.value}`, {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        }
      });
      const content = await response.json();
      console.log(content.originUrl);
      this.setState({
        originUrl: content.originUrl
      });
    })();
    event.preventDefault();
  }

  
  render () {
    return (
      <div className="form">
        <form onSubmit={this.handleSubmit}>
          <label>
            <span  
              className="form-member">
              Retrieve a url:
            </span>
            <input
              type="text"
              className="form-member"
              value={this.state.value}
              onChange={this.handleChange} />
          </label>
          <input
            type="submit"
            className="form-member"
            value="Retrieve" />
        </form>
        <span
          className="form-link">
          {this.state.originUrl}
        </span>
      </div>
    );
  }
}

export default GetUrlForm;
