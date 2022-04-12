export class CancelRequest{
    constructor(
        public user_id: number,
        public text: string,
        public accepted: boolean,
        public id? : number,
        ){}
}