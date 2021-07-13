In this project I developed a simple Movie Database System like IMDB.

The system will process several data input files and will generate
results of commands which will be read from a command input file.,

The system includes information about people and films.
• Each Person has name, surname, country and a unique id. A person in the system could
be either Artist or User.
• Each User has a unique id, name, surname and country.
• There are three kinds of Artist: Performer, Director and Writer. Each Director has a
unique id, name, surname, country and agent where he/she works. Each Writer has a
unique id, name, surname, country and writing style/type.
• There are also three types of Performers which are Actor, ChildActor and StuntPer-
former. Each Actor has a unique id, name, surname, country and height. Each ChildAc-
tor has a unique id, name, surname, country and age. Each StuntPerformer has a unique
id, name, surname, country, height and real actors' ids.
• There are four types of films in the system: Feature Film, Short Film, Documentary
and TV Series. Each film (Feature Film, Short Film, Documentary and TV Series) has
a rating score which calculated from users' average rating scores for that film.
• A unique film id, film title, language, runtime, country, directors of a film and cast are
common in all film types.
• Feature Films have a release date, budget, writers of movie and film genre in addition
to the common data.
• A Short Film has a release date, writers and genre in addition to the common data. A
Short Film' runtime should be less ( or equal) than 40 min.
• Documentaries have only a release date in addition to the common film data.
• TV Series have start date and end date of series, number of seasons, number of episodes,
genre of series and writers in addition to the common film data.
• A film may have more than one directors, writers, performers and genres in this system.
A comma will be used to separate these data.