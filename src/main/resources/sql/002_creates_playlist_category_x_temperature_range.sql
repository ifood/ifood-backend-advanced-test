INSERT INTO public.playlistcategory_x_temperature_range(
	start_temperature, end_temperature, id_playlist_category)
	VALUES (-1000.0, 10, (SELECT ID FROM public.playlist_categories WHERE name='classical'));

INSERT INTO public.playlistcategory_x_temperature_range(
	start_temperature, end_temperature, id_playlist_category)
	VALUES (10, 14, (SELECT ID FROM public.playlist_categories WHERE name='rock'));

INSERT INTO public.playlistcategory_x_temperature_range(
	start_temperature, end_temperature, id_playlist_category)
	VALUES (14, 30, (SELECT ID FROM public.playlist_categories WHERE name='pop'));

INSERT INTO public.playlistcategory_x_temperature_range(
	start_temperature, end_temperature, id_playlist_category)
	VALUES (30, 1000, (SELECT ID FROM public.playlist_categories WHERE name='party'));