export class ShortestPathRequest {
  origin: string;
  destination: string;

  constructor(origin: string, destination: string) {
    this.origin = origin;
    this.destination = destination;
  }
}
