% Facts about laptops within the budget of 40000
laptop('Acer Aspire 5', intelCoreI3, 8, 'Windows', '15.6 inch', led, integrated, '512GB SSD', 4.4).
laptop('Lenovo Ideapad S145', intelCoreI3, 8, 'Windows', '15.6 inch', led, integrated, '256GB SSD', 4.2).
laptop('HP 15s-du2071TU', intelCoreI3, 8, 'Windows', '15.6 inch', led, integrated, '1TB HDD', 4.3).
laptop('Dell Vostro 3401', intelCoreI3, 8, 'Windows', '14 inch', led, integrated, '256GB SSD', 4.0).
laptop('Asus VivoBook 14', amdRyzen3, 8, 'Windows', '14 inch', led, integrated, '1TB HDD', 4.5).

% Rule for recommending a laptop based on user preferences
recommend_laptop(Category, Budget, OS, Ram, ScreenSize, ScreenType, Graphics, StorageType, MinRating, RecommendedLaptop) :-
    laptop(LaptopName, _, Ram, OS, ScreenSize, ScreenType, Graphics, StorageType, Rating),
    Rating >= MinRating,
    fits_category(LaptopName, Category),
    fits_budget(LaptopName, Budget),
    fits_requirements(LaptopName, Ram, OS, ScreenSize, ScreenType, Graphics, StorageType),
    RecommendedLaptop = LaptopName.

% Rules to check if a laptop fits the category and budget
fits_category(_, 'Daily Use').
fits_category(Laptop, 'Programming') :- laptop(Laptop, _, _, _, _, _, _, _, _).
fits_category(Laptop, 'Gaming and Designing') :- laptop(Laptop, _, _, _, _, _, _, _, _).

% Rules to check if a laptop fits the users budget
fits_budget(_, _). % No budget constraint for now
% Add budget constraints if needed

% Rules to check if a laptop fits the users requirements
fits_requirements(Laptop, Ram, OS, ScreenSize, ScreenType, Graphics, StorageType) :-
    laptop(Laptop, _, Ram, OS, ScreenSize, ScreenType, Graphics, StorageType, _).

% Function to ask a question and get a users input
ask(Question, Response) :-
    write(Question),
    read(Response).

% Function to recommend a laptop based on user input
recommend_laptop_for_daily_use(RecommendedLaptop) :-
    ask('Do you prefer Windows OS? (yes/no): ', OS),
    ask('What is your preferred RAM size? (GB): ', Ram),
    ask('What is your preferred screen size? (e.g., "15.6 inch"): ', ScreenSize),
    ask('Do you prefer LED screen? (yes/no): ', ScreenType),
    ask('Do you need integrated graphics card? (yes/no): ', Graphics),
    ask('What type of storage do you prefer? (e.g., "512GB SSD"): ', StorageType),
    recommend_laptop('Daily Use', 40000, OS, Ram, ScreenSize, ScreenType, Graphics, StorageType, 4.0, RecommendedLaptop).

% Display the recommended laptop
display_recommended_laptop(RecommendedLaptop) :-
    write('The recommended laptop for you is: '), write(RecommendedLaptop), nl.

% Example query
?- recommend_laptop_for_daily_use(RecommendedLaptop), display_recommended_laptop(RecommendedLaptop).
