from MainTask import deserialize

def serialize_toml(data, current_key="", level=0):
    result = []
        
    for key, value in data.items():
        key = f'"{key}"'
        full_key = f'{current_key}.{key}' if current_key else key
            
        if isinstance(value, dict):
            result.append("")
            result.append(f"[{full_key}]")
            result.append(serialize_toml(value, full_key, level + 1))
                
        elif isinstance(value, list):
            items = ', '.join(f'"{item}"' for item in value)
            result.append(f'{key} = [{items}]')
                
        else:
                
            if isinstance(value, str):
                result.append(f'{key} = "{value}"')
                    
            else:
                result.append(f'{key} = {value}')
        
    return "\n".join(result)

with open('расписание.yaml', 'r', encoding='utf-8') as f:
    yaml = f.read()

result = deserialize(yaml)
toml = serialize_toml(result)

with open('расписание.toml', 'w', encoding='utf-8') as f:
    f.write(toml)
