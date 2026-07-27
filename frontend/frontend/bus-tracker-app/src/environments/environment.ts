export const environment = {
  production: false,
  get apiUrl() { return `http://${window.location.hostname}:8080`; },
  mapboxToken: 'pk.eyJ1IjoicGF2YW5rdW1hcnN3YW15IiwiYSI6ImNtNnc1c3ZpdTBkdGgyanM5b25rN2ZqcncifQ.Ls1e2W6rx3apoBsStWa5Ow',
  get websocketUrl() { return `ws://${window.location.hostname}:8080/ws`; },
  get genaiUrl() { return `http://${window.location.hostname}:8000`; }
};
