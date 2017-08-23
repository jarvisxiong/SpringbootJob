import { log } from 'util';
import { Component, OnChanges, OnInit } from '@angular/core';
import { Hero } from './hero';

import { HeroService } from './hero.service';

@Component({
	selector: 'app-root',
	templateUrl: './app.component.html',
	styleUrls: ['./app.component.css'],

})

export class AppComponent implements OnInit {
	title = 'Tour of Heroes';
	selectedHero: Hero;

	heroes: Hero[];

	constructor(private heroService: HeroService) {
	}

	getHeroes(): void {
		this.heroService.getHeroes().then(heroes => this.heroes = heroes);
	}
	

	onSelect(hero: Hero): void {
		this.selectedHero = hero;
	}
	ngOnInit(): void {
		this.getHeroes();
	}


}



