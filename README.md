# super-mario-craps
This project is a Super Mario-themed craps game created with Java JComponents. The game is displayed in a JFrame with clickable JPanels, JButtons, etc.

Craps is a gambling game where you bet money and roll dice. After you bet money, you roll a pair of dice.
For your initial roll:
<ul>
         <li>if you roll a 7 or an 11, you receive double the money you wagered;</li>
         <li>if you roll a 2, 3, or 12, you lose your wager</li>
         <li>if you roll anything else, that nunber becomes your 'goal.'</li>
</ul>

 If you didn't win or lose money on the initial roll, you keep rolling until you hit your goal, or a 7/11.
 <ul>
         <li>if you roll your goal, you receive double your wager</li>
         <li>if you roll a 7 or an 11, you lose your wager.</li>
</ul>

To play this game, run the file "CrapsDriver.java"
It should bring up this frame:
![smc-title-screen](https://user-images.githubusercontent.com/78334282/136995440-773e7c44-b7aa-48aa-9f78-e49cf9969e6a.png)

If you click anywhere inside this frame, it will bring up a character select screen:
![smc-character-select-screen](https://user-images.githubusercontent.com/78334282/136995450-c680513d-1203-4ff6-9cdd-70b881ed279e.png)

Once you pick a character, you are ready to begin playing! It will bring up the game screen:
![smc-1](https://user-images.githubusercontent.com/78334282/136995553-d5d45f04-3be3-460f-977e-2be98bce9a32.png)

The first step in craps is to bet money. You can use any of the top buttons or text field to do this. Once you have bet money, the initial roll button will become enabled:
![smc-2](https://user-images.githubusercontent.com/78334282/136995578-89234ca1-49da-4fd4-934b-0b7e1e3ec72f.png)

In this case, we rolled a three on the first roll. This causes us to lose our wager:
![smc-3](https://user-images.githubusercontent.com/78334282/136995589-f4ee8852-7fe2-4fa1-999c-0b39c06a2a41.png)

We bet more money and make another initial roll. We rolled a six, which becomes our goal. Once we have a goal, the roll again button becomes enabled:
![smc-4](https://user-images.githubusercontent.com/78334282/136995597-89e7e12a-8969-40b1-808a-b83df75eb10e.png)

If we roll a six again, we win double our wager!
![smc-5](https://user-images.githubusercontent.com/78334282/136995610-b3934bca-d5a9-4bbb-8af7-091769f78417.png)

The goal of the game is keep winning and making money.

I started making this game in high school, and I obviously don't own any rights to Super Mario. 
