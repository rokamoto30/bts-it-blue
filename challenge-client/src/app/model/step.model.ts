export class Step {
    constructor(
        public id: number, 
        public speedType: string,
        public speedDirection: string,
        public durationHours: number,
        public durationMinutes: number
    ) {}
}