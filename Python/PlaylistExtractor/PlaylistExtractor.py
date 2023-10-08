import os
import pickle
import googleapiclient.errors as google_errors
from googleapiclient.discovery import build
from google_auth_oauthlib.flow import InstalledAppFlow
from google.auth.transport.requests import Request
import sys

class PlaylistExtractor:
    __API_NAME = 'youtube'
    __API_VERSION = 'v3'
    __CLIENT_SECRET_FILE = 'client_secrets.json'
    __SCOPES = ['https://www.googleapis.com/auth/youtube.readonly']

    def __init__(self, playlistID: str, outFile: str):
        self.playlist_items = []
        self.playlistID = playlistID
        self.outFile = outFile

        self.credentials = None
        self.youtube = None
        self.channel_id = None

        if os.path.exists('token.pickle'):
            print('Loading Credentials From File...')
            with open('token.pickle', 'rb') as token:
                self.credentials = pickle.load(token)

        if not self.credentials or not self.credentials.valid:
            if self.credentials and self.credentials.expired and self.credentials.refresh_token:
                print('Refreshing Access Token...')
                self.credentials.refresh(Request())
            else:
                print('Fetching New Tokens...')

                flow = InstalledAppFlow.from_client_secrets_file(
                    self.__CLIENT_SECRET_FILE, self.__SCOPES
                )
                
                flow.run_local_server(
                    port=8080, prompt='consent', authorization_prompt_message=''
                )

                self.credentials = flow.credentials

                with open('token.pickle', 'wb') as f:
                    print('Saving Credentials for Future Use...')
                    pickle.dump(self.credentials, f)

        self.youtube = build(
            self.__API_NAME, self.__API_VERSION, credentials=self.credentials
        )

        try:
            self.my_channel = self.youtube.channelSections().list(
                part="snippet",
                mine=True
            )
            res = self.my_channel.execute()
            self.channel_id = res['items'][0]['snippet']['channelId']
        except google_errors.HttpError as e:
            print(e.error_details[0]['reason'])
            self.youtube = None
        except:
            pass # Ignore all errors
        
    # Retrieves the user's playlist.
    # Return True if successful.
    def extract_playlist(self) -> bool:
        if self.youtube is None:
            print('Failed to connect to the YouTube API!')
            return False

        request = self.youtube.playlistItems().list(
            part = 'id, snippet, status',
            playlistId=self.playlistID,
            maxResults=50
        )
    
        while request is not None:
            try:
                response = request.execute()
            except google_errors.HttpError as e:
                if e.error_details[0]['reason'] == 'playlistNotFound':
                    print('Playlist not found!')
                elif e.error_details[0]['reason'] == 'quotaExceeded':
                    print('Request could not completed due to exceeding the daily usage quota. [10K units/day]')
                return False

            for item in response['items']:   
                yt_title = item['snippet']['title']
                yt_channel = item['snippet'].get('videoOwnerChannelTitle', None)
                yt_vid_id = item['snippet']['resourceId']['videoId']
                yt_link = f'https://youtu.be/{yt_vid_id}/'
                yt_status = item['status']['privacyStatus']
                yt_data = (yt_title, yt_channel, yt_link, yt_status)
                self.playlist_items.append(yt_data) 

            request = self.youtube.playlistItems().list_next(request, response)

        self.write()
        return True     

    # Write the playlist item data to an output file
    def write(self):
        print(f'Writing to {self.outFile}...')
        if not self.playlist_items:
            print(self.playlist_items)
            return

        size = f'Video count = {len(self.playlist_items) + 1}\n\n'
        index = 0
        with open(self.outFile, 'w+', encoding='utf-8') as f:
            f.write(size)
            for video_data in self.playlist_items:
                data = f'Index: {index + 1}\nTitle: {video_data[0]}\nUploader: {video_data[1]}\nLink: {video_data[2]}\nStatus: {video_data[3]}\n\n'
                f.write(data)
                index += 1
        
if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: python PlaylistExtractor.py <playlist ID> <output file>')
        sys.exit()

    playlist = PlaylistExtractor(sys.argv[1], sys.argv[2])
    playlist.extract_playlist()