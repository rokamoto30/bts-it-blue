export class Step {
    constructor(
        public id: number, 
        public speedType: string,
        public speedDirection: string,
        public durationHour: number,
        public durationMin: number
    ) {}
}