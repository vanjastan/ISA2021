export class Boats{
    constructor(
        public id: number,
        public name: string,
        public address: string,
        public description: string,
        public grade: number,
        public availableTerms: Date,
        public price: number,
        public services: string
        ){}
}