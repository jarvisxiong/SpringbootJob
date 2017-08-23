import { HEROES } from './mock-heroes';
import { Hero } from './hero';
import { Injectable } from '@angular/core';

@Injectable()
export class HeroService {

    constructor() { }
    getHeroes(): Promise<Hero[]> {
        return Promise.resolve(HEROES);
    }
}
