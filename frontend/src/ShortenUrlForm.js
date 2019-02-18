import React, { Component } from 'react';
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
    (async () => {
      const response = await fetch(`http://localhost:8080/short/shortenUrl`, {
        method: 'PUT',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          originUrl: this.state.value,
          shortUrl: null
        })
      });
      const content = await response.json();
      this.setState({
        shortUrl: content.shortUrl
      });
    })();
    event.preventDefault();
  }

  
  render () {
    const shortLink = `http://localhost:8080/short/${this.state.shortUrl}`;
    return (
      <div>
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
        <a
          target="_blank"
          rel="noopener noreferrer"
          href={shortLink}>
          {this.state.shortUrl.length > 0 ? shortLink : ''}
        </a>
      </div>
    );
  }
}

export default ShortenUrlForm;
