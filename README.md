# OpenWebRX Client
A standalone client library for OpenWebRX(+) receivers

## About
This project is a client library for [OpenWebRX](https://www.openwebrx.de/) and [OpenWebRX+](https://github.com/luarvique/openwebrx) online SDRs written in Java.  
It aims to implement most of OWRX's features, including audio (obviously), FFT, demodulators, and more.  
Currently, the project is in WIP state and is not really usable.  

## Features
- [x] Audio
  - [x] Standard audio
  - [x] Higher sample rate "HD" audio
- [x] FFT
  - [x] Main FFT
  - [x] Secondary FFT (needs more testing)
- [x] Bookmarks (server-side only)
- [x] ADPCM compression (both audio and FFT)
- [x] Server state
  - [x] Available profiles
  - [x] Server features
  - [x] Available modes
  - [x] Bandplan
  - [x] Server configuration
  - [x] Receiver details
- [x] Receiver status
  - [x] Connected clients
  - [x] CPU usage and temperature
  - [x] Device's battery condition
- [x] Profile switching
  - [x] Magic key support
- [x] Mode switching
- [x] Center frequency switching
  - [x] Magic key support
- [x] Chat (both sending and receiving)
- [x] Signal meter
- [x] Analog modes
- [ ] Digital modes
  - [x] Radio Data System
  - [x] CW
    - [x] CW Decoder
    - [x] CW Skimmer
  - [x] FT8
  - [x] FT4
  - [ ] ACARS
  - [ ] VDL-M2
  - [ ] ISM
  - [ ] RTTY
  - [ ] DSB
  - [ ] Possibly more

## How to use
*TODO*
