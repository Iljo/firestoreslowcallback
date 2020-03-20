Code to show slow callback response in Firestore while device is offline.
Issue is reported on [Google's IssueTracker](https://issuetracker.google.com/issues/150891237)

Database consists of only one collection called `notes` and one document  
in it, with id `2020-03-20`. So, database is as small as possible, but
every time we query for data on date that doesn't exist (while offline)
response gets slower and slower.

Here is my logcat filtered by `FirestoreIssue` tag, which shows that:
```
21:03:56.840 Selected 2020-03-20, waiting for response...
21:03:57.099 Response: SnapshotMetadata{hasPendingWrites=false, isFromCache=true}

21:04:05.860 Selected 2020-03-19, waiting for response...
21:04:11.028 Response: SnapshotMetadata{hasPendingWrites=false, isFromCache=true}

21:04:17.901 Selected 2020-03-18, waiting for response...
21:04:20.876 Response: SnapshotMetadata{hasPendingWrites=false, isFromCache=true}

21:04:25.890 Selected 2020-03-17, waiting for response...
21:04:36.368 Response: SnapshotMetadata{hasPendingWrites=false, isFromCache=true}

21:04:41.139 Selected 2020-03-16, waiting for response...
21:04:52.049 Response: SnapshotMetadata{hasPendingWrites=false, isFromCache=true}

21:05:01.818 Selected 2020-03-15, waiting for response...
21:05:21.712 Response: SnapshotMetadata{hasPendingWrites=false, isFromCache=true}
```

