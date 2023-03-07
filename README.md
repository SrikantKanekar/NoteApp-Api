# Note Api

Rest Api for Note Android and iOS applications

Android client - https://github.com/SrikantKanekar/NoteApp-Android
iOS client - https://github.com/SrikantKanekar/NoteApp-iOS

## API Reference

### Authentication

#### Login

```http
  POST /auth/login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `email` | `string` | **Required**. Your email |
| `password` | `string` | **Required**. Your password |

#### Register

```http
  POST /auth/register
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Your username |
| `email` | `string` | **Required**. Your email |
| `password1` | `string` | **Required**. Your password |
| `password2` | `string` | **Required**. Your confirmed password |

#### Change password

```http
  PUT /auth/reset-password
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `oldPassword` | `string` | **Required**. Your old password |
| `newPassword` | `string` | **Required**. Your new password |
| `confirmPassword` | `string` | **Required**. Your confirmed new password |

### Notes

#### Get all notes

```http
  GET /notes
```

#### Get note by Id

```http
  GET /notes/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. Your note id |

#### Insert or update notes

```http
  POST /notes
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `notes` | `List<Note>` | **Required**. Your note list |

#### Delete notes

```http
  DELETE /notes
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `noteIds` | `List<string>` | **Required**. Your note list ids |

### Labels

#### Get all labels

```http
  GET /labels
```

#### Get label by Id

```http
  GET /labels/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `string` | **Required**. Your label id |

#### Insert or update labels

```http
  POST /labels
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `labels` | `List<Label>` | **Required**. Your label list |

#### Delete labels

```http
  DELETE /labels
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `labelIds` | `List<string>` | **Required**. Your label list ids |
